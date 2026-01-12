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
            val profilePicPart = addEmployeeRequest.profile_pic
                ?: return NetworkResource.Error("Profile picture required")

            val resumePart = addEmployeeRequest.resume
                ?: return NetworkResource.Error("Resume required")

            val parts = mutableMapOf<String, RequestBody>().apply {
                put("first_name", addEmployeeRequest.first_name.toRequestBody())
                put("last_name", addEmployeeRequest.last_name.toRequestBody())
                put("date_of_birth", addEmployeeRequest.date_of_birth.toRequestBody())
                put("designation", addEmployeeRequest.designationId.toRequestBody())
                put("gender", addEmployeeRequest.gender.toRequestBody())
                put("mobile_number", addEmployeeRequest.mobile_number.toRequestBody())
                put("email", addEmployeeRequest.email.toRequestBody())
                put("address", addEmployeeRequest.address.toRequestBody())
                put("contract_period", addEmployeeRequest.contract_period.toRequestBody())
                put("total_salary", addEmployeeRequest.total_salary.toRequestBody())
                putAll(buildMonthlyPaymentsParts(addEmployeeRequest.monthly_payments))
            }

            api.addEmployee(
                parts = parts,
                profile_pic = profilePicPart.toMultipart("profile_pic"),
                resume = resumePart.toMultipart("resume")
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
    toRequestBody("text/plain".toMediaType())

fun Int.toRequestBody(): RequestBody =
    toString().toRequestBody("text/plain".toMediaType())

fun Double.toRequestBody(): RequestBody =
    toString().toRequestBody("text/plain".toMediaType())

fun File.toMultipart(key: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        key,
        name,
        asRequestBody("multipart/form-data".toMediaType())
    )

fun buildMonthlyPaymentsParts(
    payments: List<PaymentDetail>
): Map<String, RequestBody> {

    val map = mutableMapOf<String, RequestBody>()

    payments.forEachIndexed { index, payment ->
        map["monthly_payments[$index][payment_date]"] =
            payment.date.toRequestBody()

        map["monthly_payments[$index][amount_percentage]"] =
            payment.amountPercentage.toDouble().toRequestBody()

        map["monthly_payments[$index][remarks]"] =
            payment.remarks.toRequestBody()

        map["monthly_payments[$index][amount]"] =
            payment.amount.toDouble().toRequestBody()
    }

    return map
}
