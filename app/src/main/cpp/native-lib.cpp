#include <jni.h>
#include <string>
#include <jni.h>
#include <jni.h>
#include <jni.h>
#include <jni.h>


//extern "C" jstring
//Java_com_example_ndk_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}

extern "C" jstring
Java_com_example_ndk_MainActivity_getmyJNTValue(JNIEnv *env, jobject thiz) {
    std::string value = "my test cpp";
    return env->NewStringUTF(value.c_str());
}

extern "C" jint
Java_com_example_ndk_MainActivity_isIntCpp(JNIEnv *env, jobject thiz) {
    return 12333;
}

extern "C" jboolean
Java_com_example_ndk_MainActivity_isBooleanCpp(JNIEnv *env, jobject thiz) {
    if (1 == 1) {
        return true;
    }
    return false;
}