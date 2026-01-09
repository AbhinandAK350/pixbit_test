package com.abhinand.pixbittest.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.abhinand.pixbittest.R
import com.abhinand.pixbittest.core.theme.Primary
import com.abhinand.pixbittest.core.theme.interMedium
import com.abhinand.pixbittest.core.theme.interRegular

@Composable
fun EmployeeItem(
    modifier: Modifier = Modifier,
    employeeImageUrl: String,
    onItemClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick() })
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(employeeImageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.img_employee_placeholder),
            contentDescription = "Employee Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(49.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = modifier.width(9.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                "Employee Name",
                fontFamily = interMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = Primary
            )
            Spacer(modifier = modifier.height(6.dp))
            Text(
                "Phone",
                fontSize = 14.sp,
                fontFamily = interRegular,
                color = Color(0xFF2A5277),
                fontWeight = FontWeight.W400
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewEmployee() {
    EmployeeItem(employeeImageUrl = "", onItemClick = {})
}