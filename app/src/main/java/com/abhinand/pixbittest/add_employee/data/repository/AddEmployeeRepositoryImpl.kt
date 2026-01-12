package com.abhinand.pixbittest.add_employee.data.repository

import android.util.Log
import com.abhinand.pixbittest.add_employee.data.mapper.toDesignation
import com.abhinand.pixbittest.add_employee.data.remote.api.AddEmployeeApi
import com.abhinand.pixbittest.add_employee.data.remote.dto.AddEmployeeRequest
import com.abhinand.pixbittest.add_employee.domain.model.Designation
import com.abhinand.pixbittest.add_employee.domain.repository.AddEmployeeRepository
import com.abhinand.pixbittest.add_employee.presentation.components.steps.PaymentDetail
import com.abhinand.pixbittest.core.network.NetworkResource
import com.abhinand.pixbittest.core.network.NetworkUtils
import com.abhinand.pixbittest.core.network.parseErrorBody
import com.abhinand.pixbittest.core.network.toNetworkError
import com.abhinand.pixbittest.core.network.toUserMessage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

class AddEmployeeRepositoryImpl @Inject constructor(
    private val api: AddEmployeeApi,
    private val networkUtils: NetworkUtils
) : AddEmployeeRepository {
    override suspend fun getDesignations(): NetworkResource<List<Designation>> {
        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }
        return try {
            val response = api.getDesignations()
            if (response.isNotEmpty()) {
                Log.e("AddEmployeeRepository", "desig: $response")
                NetworkResource.Success(response.map { it.toDesignation() })
            } else {
                Log.e("AddEmployeeRepository", "Error")
                NetworkResource.Error("Designation fetch failed")
            }
        } catch (e: Exception) {
            Log.e("RegisterRepositoryImpl", "register: ", e)
            val errorMessage = when (e) {
                is HttpException -> {
                    e.parseErrorBody()?.error
                        ?: e.toNetworkError().toUserMessage()
                }

                else -> e.toNetworkError().toUserMessage()
            }
            NetworkResource.Error(errorMessage)
        }
    }

    override suspend fun addEmployee(addEmployeeRequest: AddEmployeeRequest): NetworkResource<Unit> {

        if (!networkUtils.isNetworkAvailable()) {
            return NetworkResource.Error("No internet connection")
        }

        return try {

            api.addEmployee(
                firstName = addEmployeeRequest.first_name.toRequestBody(),
                lastName = addEmployeeRequest.last_name.toRequestBody(),
                dateOfBirth = addEmployeeRequest.date_of_birth.toRequestBody(),
                designation = addEmployeeRequest.designationId.toString().toRequestBody(),
                gender = addEmployeeRequest.gender.toRequestBody(),
                mobileNumber = addEmployeeRequest.mobile_number.toRequestBody(),
                email = addEmployeeRequest.email.toRequestBody(),
                address = addEmployeeRequest.address.toRequestBody(),

                profilePic = addEmployeeRequest.profile_pic!!.toMultipart("profile_pic"),
                resume = addEmployeeRequest.resume!!.toMultipart("resume"),

                contractPeriod = addEmployeeRequest.contract_period.toString().toRequestBody(),
                totalSalary = addEmployeeRequest.total_salary.toString().toRequestBody(),

                monthlyPayments = addEmployeeRequest.monthly_payments.toMultipartMap()
            )

            NetworkResource.Success(Unit)

        } catch (e: Exception) {
            Log.e("AddEmployeeRepository", "add: ", e)
            val message = when (e) {
                is HttpException ->
                    e.parseErrorBody()?.error
                        ?: e.toNetworkError().toUserMessage()

                else ->
                    e.toNetworkError().toUserMessage()
            }
            NetworkResource.Error(message)
        }
    }
}

fun String.toRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaType())

fun File.toMultipart(key: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        name = key,
        filename = name,
        body = asRequestBody("application/octet-stream".toMediaType())
    )

fun List<PaymentDetail>.toMultipartMap(): Map<String, RequestBody> {
    val map = mutableMapOf<String, RequestBody>()

    forEachIndexed { index, item ->
        map["monthly_payments[$index][payment_date]"] =
            item.date.toRequestBody()

        map["monthly_payments[$index][amount_percentage]"] =
            item.amountPercentage.toString().toRequestBody()

        map["monthly_payments[$index][remarks]"] =
            item.remarks.toRequestBody()

        map["monthly_payments[$index][amount]"] =
            item.amount.toString().toRequestBody()
    }

    return map
}

