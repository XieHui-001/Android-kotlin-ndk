//
// Created by Administrator on 2022/3/23.
//

#include "jni.h"
#include "string"

extern "C" jstring
Java_com_example_ndk_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
