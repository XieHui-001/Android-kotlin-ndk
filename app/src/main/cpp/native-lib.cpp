#include <jni.h>
#include <jni.h>
#include <jni.h>
#include <string>


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
    int a,b,c = 10;
    for (int i = 0; i < c + b + a ; ++i) {
        printf("%s",i);
    }
    return  c + b + a;
}



extern "C" jboolean
Java_com_example_ndk_MainActivity_isBooleanCpp(JNIEnv *env, jobject thiz) {
    return 1 == 1;
}


class x {
    static thread_local std::string str;
};