package com.abhinand.pixbittest.profile_details.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.navigation.Action
import com.abhinand.pixbittest.core.theme.Container
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.Secondary
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.core.theme.interRegular
import com.abhinand.pixbittest.core.theme.interSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDetailsScreen(modifier: Modifier = Modifier, onNavigate: (Action) -> Unit) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    modifier = modifier.padding(start = 10.dp),
                    text = stringResource(R.string.profile_details),
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    fontSize = 20.sp
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Container),
            navigationIcon = {
                IconButton(onClick = { onNavigate(Action.Pop) }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBackIosNew,
                        contentDescription = null
                    )
                }
            },
        )
    }, containerColor = Color(0xFFFBFDFF)) { contentPadding ->

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding() + 24.dp,
                bottom = contentPadding.calculateBottomPadding() + 25.dp
            )
        ) {

            item {
                ProfileHeader()
                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                ContactInfoCard()
                Spacer(modifier = modifier.height(8.dp))
            }

            item {
                ResumeCard()
                Spacer(modifier = modifier.height(24.dp))
            }

            item {
                SalarySchemeHeader()
                Spacer(modifier = modifier.height(24.dp))
            }

            items(3) { item ->
                SalaryMonthCard(
                    id = 0,
                    month = "Month 1",
                    date = "12-12-2024",
                    percentage = "20%",
                    amount = "₹ 12,000",
                    remarks = "First Salary",
                    onDeleteClick = {}
                )
            }
        }

    }
}

@Composable
private fun ProfileHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://picsum.photos/200",
            contentDescription = null,
            modifier = modifier
                .size(125.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = modifier.height(24.dp))

        Text(
            text = "John Joy",
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            fontFamily = interMedium,
            letterSpacing = 0.sp,
            color = Primary
        )

        Spacer(modifier = modifier.height(4.dp))

        Text(
            text = "Supervisor",
            fontSize = 14.sp,
            color = Color(0xFF2A5277),
            fontFamily = interRegular,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.W400
        )
    }
}

@Composable
private fun ContactInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, Color(0xFFE2F1FF)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            TwoColumnRow(
                leftTitle = "Contact Number",
                leftValue = "+91 8412094567",
                rightTitle = "Email",
                rightValue = "johnjoy@mail.com"
            )

            TwoColumnRow(
                leftTitle = "Date of birth",
                leftValue = "12-12-2022",
                rightTitle = "Gender",
                rightValue = "Male"
            )

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Address",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = Primary,
                    letterSpacing = 0.sp,
                    fontFamily = interMedium
                )
                Text(
                    text = "12 street, The Marine World, los angeles",
                    fontSize = 14.sp,
                    letterSpacing = 0.sp,
                    fontFamily = interRegular,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF2A5277)
                )
            }
        }
    }
}

@Composable
private fun TwoColumnRow(
    leftTitle: String,
    leftValue: String,
    rightTitle: String,
    rightValue: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        InfoColumn(
            modifier = Modifier.weight(1f),
            title = leftTitle,
            value = leftValue
        )
        InfoColumn(
            modifier = Modifier.weight(1f),
            title = rightTitle,
            value = rightValue
        )
    }
}

@Composable
private fun InfoColumn(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = interMedium,
            color = Primary,
            letterSpacing = 0.sp
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            fontFamily = interRegular,
            letterSpacing = 0.sp,
            color = Color(0xFF2A5277)
        )
    }
}

@Composable
private fun ResumeCard() {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(0.5.dp, color = Color(0xFFE2F1FF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_pdf),
                contentDescription = null,
                modifier = Modifier
                    .width(42.dp)
                    .height(52.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Resume File",
                    fontWeight = FontWeight.W600,
                    fontFamily = interSemiBold,
                    letterSpacing = 0.sp,
                    fontSize = 14.sp
                )
                Text(
                    text = "Updated 01-01-2020",
                    fontSize = 12.sp,
                    fontFamily = interRegular,
                    letterSpacing = 0.sp,
                    color = Color(0xFFB1B1B1)
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE2F1FF))
                    .padding(horizontal = 8.dp, vertical = 7.dp)
            ) {
                Text(
                    text = "View",
                    fontSize = 14.sp,
                    color = Secondary,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 0.sp,
                    fontFamily = interRegular
                )
            }
        }
    }
}

@Composable
private fun SalarySchemeHeader() {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE2F1FF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.salary_scheme),
                fontWeight = FontWeight.W600,
                fontFamily = interSemiBold,
                letterSpacing = 0.sp,
                fontSize = 14.sp
            )
            Text(
                text = "3 Months   ₹60,000",
                color = Secondary,
                fontWeight = FontWeight.W500,
                fontFamily = interMedium,
                letterSpacing = 0.sp,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun SalaryMonthCard(
    id: Int,
    month: String,
    date: String,
    percentage: String,
    amount: String,
    remarks: String,
    showDeleteButton: Boolean = false,
    onDeleteClick: (Int) -> Unit
) {
    Column {
        Text(
            text = month,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF2979FF)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(0.5.dp, Color(0xFFE2F1FF))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                TwoColumnRow(
                    stringResource(R.string.payment_date), date,
                    stringResource(R.string.payment_amount, percentage), amount
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.remarks),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = interMedium,
                    color = Primary,
                    letterSpacing = 0.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = remarks,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        fontFamily = interRegular,
                        letterSpacing = 0.sp,
                        color = Color(0xFF2A5277)
                    )

                    if (showDeleteButton) {
                        IconButton(onClick = { onDeleteClick(id) }) {
                            Image(
                                modifier = Modifier.size(28.dp),
                                painter = painterResource(R.drawable.ic_delete),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}