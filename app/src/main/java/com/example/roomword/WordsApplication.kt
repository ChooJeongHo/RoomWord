package com.example.roomword

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    //  프로세스와 함께 제거됨
    val applicationScope = CoroutineScope(SupervisorJob())

    // 애플리케이션이 시작될 때가 아니라 데이터베이스와 저장소가 필요할 때만 생성되도록 사용
    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}