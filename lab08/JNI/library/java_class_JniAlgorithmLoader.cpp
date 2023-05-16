#include <jni.h>      
#include <iostream>
#include "java_class_JniAlgorithmLoader.h"

JNIEXPORT jobjectArray JNICALL Java_java_1class_JniAlgorithmLoader_loadAlgorithm(JNIEnv* env, jobject obj, jobjectArray array1, jobjectArray array2)
  {

    // get size of arrays
    jsize size1 = env->GetArrayLength(array1);
    jsize size2 = env->GetArrayLength(array2);

    // check if arrays have same size
    if (size1 != size2)
    {
        //throw exception if not
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        env->ThrowNew(exceptionClass, "Array sizes do not match");
        return nullptr;
    }

    // Pobierz pierwszy element z tablicy wejściowej (zakładamy, że tablica jest niepusta)
    jobject firstRow = env->GetObjectArrayElement(array1, 0);

    // Pobierz rozmiar pierwszego wiersza
    jsize rowSize = env->GetArrayLength(static_cast<jarray>(firstRow));

    // Sprawdź, czy wszystkie wiersze mają taki sam rozmiar
    for (jsize i = 1; i < size1; ++i)
    {
        jobject row = env->GetObjectArrayElement(array1, i);
        jsize currentRowSize = env->GetArrayLength(static_cast<jarray>(row));

        if (currentRowSize != rowSize)
        {
            // check if rows have same size
            jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
            env->ThrowNew(exceptionClass, "Inconsistent row sizes");
            return nullptr;
        }
    }

    // create new result matrix
    jobjectArray resultArray = env->NewObjectArray(size1, env->GetObjectClass(firstRow), nullptr);

   
    for (jsize i = 0; i < size1; ++i)
    {
        //get row from first array
        jobject inputRow = env->GetObjectArrayElement(array1, i);
        jdoubleArray inputArray = static_cast<jdoubleArray>(inputRow);
        jdouble* inputData = env->GetDoubleArrayElements(inputArray, nullptr);

        //get row from second array
        jobject kernelRow = env->GetObjectArrayElement(array2, i);
        jdoubleArray kernelArray = static_cast<jdoubleArray>(kernelRow);
        jdouble* kernelData = env->GetDoubleArrayElements(kernelArray, nullptr);

        // create result row
        jdoubleArray resultRow = env->NewDoubleArray(rowSize);
        jdouble* resultData = env->GetDoubleArrayElements(resultRow, nullptr);

        // calculate splot for row
        for (jsize j = 0; j < rowSize; ++j)
        {
            resultData[j] = 0.0;

            
            for (jsize k = 0; k < rowSize; k++)
            {
                // Calculate splot for element (i, j)
                resultData[j] += inputData[k] * kernelData[rowSize - k - 1];
            }
        }

        // save row to result matrix
        env->SetDoubleArrayRegion(resultRow, 0, rowSize, resultData);
        env->SetObjectArrayElement(resultArray, i, resultRow);

        // Free memory
        env->ReleaseDoubleArrayElements(inputArray, inputData, 0);
        env->ReleaseDoubleArrayElements(kernelArray, kernelData, 0);
        env->ReleaseDoubleArrayElements(resultRow, resultData, 0);
    }

    return resultArray;

  }

