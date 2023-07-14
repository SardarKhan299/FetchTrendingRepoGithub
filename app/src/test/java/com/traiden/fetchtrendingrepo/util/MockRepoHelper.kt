package com.traiden.fetchtrendingrepo.util

import com.traiden.fetchtrendingrepo.domain.Items
import com.traiden.fetchtrendingrepo.domain.Owner
import com.traiden.fetchtrendingrepo.domain.Repository

class MockRepoHelper {
    companion object{
        val repositories = Items(listOf(
            Repository("Repo 1", Owner("","OwnerName"), "Description 1", "", 4),
            Repository("Repo 2", Owner("","OwnerName2"), "Description 2", "", 5)
        ))
        fun getMockRepo():Items{
            return repositories
        }
        fun getFilteredList():Items{
            return repositories
        }
    }
}