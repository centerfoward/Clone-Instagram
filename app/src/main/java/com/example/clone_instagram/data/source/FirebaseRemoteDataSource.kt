package com.example.clone_instagram.data.source

import com.example.clone_instagram.data.model.userData
import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRemoteDataSource @Inject constructor(
    private val data : FirebaseFirestore
) {
    fun postData(uid : String, name : String) : Flow<ResponseResult<Unit>> = flow{
        emit(ResponseResult.Loading)

        try{
            //TODO UID 암호화 필요
            data.collection("User_Info")
                .add(userData(
                    name = name,
                    uid = uid
                )).await()
            emit(ResponseResult.Success(Unit))

        }catch (e : Exception){
            emit(ResponseResult.Failure(e))
        }
    }

    fun getData(uid : String) : Flow<ResponseResult<DocumentSnapshot>> = flow{
        emit(ResponseResult.Loading)
        try{
            val querySnapShot = data.collection("User_Info")
                .whereEqualTo("uid",uid)
                .get()
                .await()
            if(!querySnapShot.isEmpty){
                val document = querySnapShot.documents.first()
                emit(ResponseResult.Success(document))
            }else{
                emit(ResponseResult.Failure(Exception("유저 정보가 존재하지 않습니다")))
            }
        }catch (e : Exception){
            emit(ResponseResult.Failure(e))
        }
    }

    fun validNickname(name : String) : Flow<ResponseResult<String>> = flow {
        emit(ResponseResult.Loading)
        try {
            val querySnapShot = data.collection("UserInfo")
                .whereEqualTo("name",name)
                .get()
                .await()
            if(!querySnapShot.isEmpty){
                emit(ResponseResult.Failure(Exception("닉네임이 중복됩니다")))
            }else{
                emit(ResponseResult.Success("사용할 수 있는 닉네임입니다"))
            }
        }catch (e : Exception){
            emit(ResponseResult.Failure(e))
        }
    }
}