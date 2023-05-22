#include <jni.h>
#include "java_class_JniAlgorithmLoader.h"

JNIEXPORT jobjectArray JNICALL Java_java_1class_JniAlgorithmLoader_loadAlgorithm(JNIEnv* env, jobject obj, jobjectArray data, jobjectArray kernel)
{
    // Get size of arrays
    int size1 = env->GetArrayLength(data);
    int size2 = env->GetArrayLength(kernel);

    // Check if arrays have the same size
    if (size1 != size2) {
        return nullptr;
    }

    // Get row size
    int rowSize = 0;
    
    jdoubleArray firstRow = (jdoubleArray)env->GetObjectArrayElement(data, 0);
    rowSize = env->GetArrayLength(firstRow);
    env->DeleteLocalRef(firstRow);
    

    // Create the result array
    jclass doubleArrayClass = env->FindClass("[D");
    jobjectArray resultArray = env->NewObjectArray(size1, doubleArrayClass, nullptr);

    // Perform convolution for each row
    for (int i = 0; i < size1; i++) {
        // Get row from the first array
        jdoubleArray inputRow = (jdoubleArray)env->GetObjectArrayElement(data, i);
        jdouble* inputData = env->GetDoubleArrayElements(inputRow, nullptr);

        // Get row from the second array
        jdoubleArray kernelRow = (jdoubleArray)env->GetObjectArrayElement(kernel, i);
        jdouble* kernelData = env->GetDoubleArrayElements(kernelRow, nullptr);

        // Create result row
        jdoubleArray resultRow = env->NewDoubleArray(rowSize);
        jdouble* resultData = new jdouble[rowSize];

        // Calculate convolution for row
        for (int j = 0; j < rowSize; j++) {
            resultData[j] = 0.0;

            for (int k = 0; k < rowSize; k++) {
                // Calculate convolution for element (i, j)
                resultData[j] += inputData[k] * kernelData[rowSize - k - 1];
            }
        }

        // Set row to the result array
        env->SetDoubleArrayRegion(resultRow, 0, rowSize, resultData);
        env->SetObjectArrayElement(resultArray, i, resultRow);

        // Release memory
        env->ReleaseDoubleArrayElements(inputRow, inputData, JNI_ABORT);
        env->ReleaseDoubleArrayElements(kernelRow, kernelData, JNI_ABORT);
        env->DeleteLocalRef(inputRow);
        env->DeleteLocalRef(kernelRow);
        delete[] resultData;
    }

    return resultArray;
}