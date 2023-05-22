#include <jni.h>
#include "java_class_JniAlgorithmLoader.h"

JNIEXPORT jobjectArray JNICALL Java_java_1class_JniAlgorithmLoader_loadAlgorithm(JNIEnv* env, jobject obj, jobjectArray array1, jobjectArray array2)
{
    // Get size of arrays
    jsize size1 = env->GetArrayLength(array1);
    jsize size2 = env->GetArrayLength(array2);

    // Check if arrays have the same size
    if (size1 != size2) {
        return nullptr;
    }

    // Get row size
    jsize rowSize = 0;
    if (size1 > 0) {
        jobject firstRow = env->GetObjectArrayElement(array1, 0);
        jdoubleArray doubleArray = static_cast<jdoubleArray>(firstRow);
        rowSize = env->GetArrayLength(doubleArray);
    }

    // Create the result array
    jclass doubleArrayClass = env->FindClass("[D");
    jobjectArray resultArray = env->NewObjectArray(size1, doubleArrayClass, nullptr);

    // Perform convolution for each row
    for (jsize i = 0; i < size1; i++) {
        // Get row from the first array
        jobject inputRow = env->GetObjectArrayElement(array1, i);
        jdoubleArray inputArray = static_cast<jdoubleArray>(inputRow);
        jdouble* inputData = env->GetDoubleArrayElements(inputArray, nullptr);

        // Get row from the second array
        jobject kernelRow = env->GetObjectArrayElement(array2, i);
        jdoubleArray kernelArray = static_cast<jdoubleArray>(kernelRow);
        jdouble* kernelData = env->GetDoubleArrayElements(kernelArray, nullptr);

        // Create result row
        jdoubleArray resultRow = env->NewDoubleArray(rowSize);
        jdouble* resultData = env->GetDoubleArrayElements(resultRow, nullptr);

        // Calculate convolution for row
        for (jsize j = 0; j < rowSize; j++) {
            resultData[j] = 0.0;

            for (jsize k = 0; k < rowSize; k++) {
                // Calculate convolution for element (i, j)
                resultData[j] += inputData[k] * kernelData[rowSize - k - 1];
            }
        }

        // Set row to the result array
        env->SetObjectArrayElement(resultArray, i, resultRow);

        // Release memory
        env->ReleaseDoubleArrayElements(inputArray, inputData, 0);
        env->ReleaseDoubleArrayElements(kernelArray, kernelData, 0);
        env->ReleaseDoubleArrayElements(resultRow, resultData, 0);
    }

    return resultArray;
}
