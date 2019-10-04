package com.lambdaschool.congressdata

import com.lambdaschool.congressdata.importedjava.NetworkAdapter
import com.lambdaschool.congressdata.model.ApiRetro
import org.mockito.Mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import retrofit2.Retrofit
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

class MathAbsTestParams : Spek({
    val ezNetworkAdapter= NetworkAdapter()
   /* val testCases = mapOf(
        listOf<Long>(100,-100) to 0L,
        listOf<Long>(0,-100) to 100L,
        listOf<Long>(-100,100) to 0L,
        listOf<Long>(50,-75) to 25L
    )*/
    test("google test"){
        val test =NetworkAdapter.httpGetRequest("http://google.com")
        assertNotSame("google", test)
    }



      /*  testCases.forEach { value, expectedValue ->
            it("${value[0]} +${value[1]}, $expectedValue") {
                assertEquals(expectedValue, ezMath.diffAbsValue(value[0],value[1]))
            }
        }*/

})

class RetrofitCongressTest : Spek({
    val ezNetworkAdapter= ApiRetro.Factory.create()

    /* val testCases = mapOf(
         listOf<Long>(100,-100) to 0L,
         listOf<Long>(0,-100) to 100L,
         listOf<Long>(-100,100) to 0L,
         listOf<Long>(50,-75) to 25L
     )*/
    test("google test"){

            val response = ezNetworkAdapter.getMemmbersAll()




        val test =NetworkAdapter.httpGetRequest("http://google.com")
        assertNotSame("google", test)
    }



    /*  testCases.forEach { value, expectedValue ->
          it("${value[0]} +${value[1]}, $expectedValue") {
              assertEquals(expectedValue, ezMath.diffAbsValue(value[0],value[1]))
          }
      }*/

})
