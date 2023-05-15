#include <jni.h>      
#include <iostream>
#include "java_class_JniAlgorithmLoader.h"

JNIEXPORT jobjectArray JNICALL Java_java_1class_JniAlgorithmLoader_loadAlgorithm(JNIEnv* env, jobject obj, jobjectArray array1, jobjectArray array2)
  {

    std::cout << "hello v2" << std::endl;

    // Pobranie rozmiarów tablic
    jsize numRows = env->GetArrayLength(array1);
    jsize numCols = env->GetArrayLength(static_cast<jobjectArray>(env->GetObjectArrayElement(array1, 0)));

    // Tworzenie jobjectArray o rozmiarze numRows
    jobjectArray resultArray = env->NewObjectArray(numRows, env->GetObjectClass(array1), nullptr);

    // Inicjalizacja elementów tablicy
    for (int i = 0; i < numRows; i++) {
        // Pobranie wiersza z array1
        jobjectArray rowArray1 = static_cast<jobjectArray>(env->GetObjectArrayElement(array1, i));

        // Tworzenie jintArray o rozmiarze numCols
        jintArray rowArray2 = env->NewIntArray(numCols);

        // Pobranie danych z array2
        jint* rowData2 = env->GetIntArrayElements(static_cast<jintArray>(env->GetObjectArrayElement(array2, i)), nullptr);

        // Ustawienie danych w rowArray2
        env->SetIntArrayRegion(rowArray2, 0, numCols, rowData2);

        // Ustawienie wiersza w resultArray
        env->SetObjectArrayElement(resultArray, i, rowArray2);

        // Zwolnienie pamięci
        env->ReleaseIntArrayElements(static_cast<jintArray>(env->GetObjectArrayElement(array2, i)), rowData2, JNI_ABORT);
        env->DeleteLocalRef(rowArray1);
        env->DeleteLocalRef(rowArray2);
    }

    return resultArray;
  }

