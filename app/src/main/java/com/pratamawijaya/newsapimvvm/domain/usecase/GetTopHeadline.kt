package com.pratamawijaya.newsapimvvm.domain.usecase

import com.pratamawijaya.newsapimvvm.data.repository.NewsRepository
import com.pratamawijaya.newsapimvvm.domain.Article
import com.pratamawijaya.newsapimvvm.utils.exceptions.Failure
import com.pratamawijaya.newsapimvvm.utils.functional.Either
import java.lang.Exception

class GetTopHeadline(private val articleRepo: NewsRepository) {

    suspend fun execute(params: Params): Either<Failure, List<Article>> {
        return try {
            val articles = articleRepo.getTopHeadlines(params.country, params.category)
            Either.Right(articles)
        } catch (ex: Exception) {
            Either.Left(GetTopHeadlineFailure(ex))
        }
    }

    data class Params(val country: String,
                      val category: String)

    data class GetTopHeadlineFailure(val error: Exception) : Failure.FeatureFailure(error)
}