package com.example.roomword

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// DAO를 생성자의 개인 속성으로 선언합니다. DAO를 전달합니다
// DAO에 대한 액세스를 제공하는 레포지토리를 만듭니다.
class WordRepository(private val wordDao: WordDao) {

    // 룸은 별도의 스레드에서 모든 쿼리를 실행합니다.
    // 데이터가 변경되면 관찰자에게 알립니다.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // Room은 메인 스레드에서 일시 중단 쿼리를 실행하므로
    // 메인 스레드에서 데이터베이스 작업을 오래 실행하지 않도록 하기 위해 다른 작업을 구현할 필요가 없다.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}