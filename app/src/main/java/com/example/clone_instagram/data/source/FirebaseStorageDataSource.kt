package com.example.clone_instagram.data.source

import com.example.clone_instagram.utils.ResponseResult
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseStorageDataSource @Inject constructor(
    private val data : StorageReference
) {

    fun getData(uid : String) : Flow<ResponseResult<Unit>> = flow{
        emit(ResponseResult.Loading)
        try{
            data.child(uid)
        }catch (e : Exception){

        }

    }
}