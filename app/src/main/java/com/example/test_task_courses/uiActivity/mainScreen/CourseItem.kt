package com.example.test_task_courses.uiActivity.mainScreen


import android.os.Build

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.test_task_courses.R

@Composable
fun CourseItem(
    course: UiCourse,
    onFavoriteClick: () -> Unit
) {

    val blurEffect = BlurEffect(16f, 16f, TileMode.Clamp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF24252A))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.cover),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(144.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 4.dp, bottom = 4.dp)
                        .wrapContentSize()
                ) {
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.info),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.star_fill),
                                contentDescription = "Рейтинг",
                                tint = Color(0xFF12B956),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${course.rate}",
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.infod),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Text(
                            text = course.formattedPublishDate,
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 12.dp, top = 12.dp)
                        .wrapContentSize()
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(50.dp))
                                .graphicsLayer {
                                    compositingStrategy = CompositingStrategy.Offscreen
                                    renderEffect = blurEffect
                                }
                                .background(Color(0x4D32333A))
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color(0x4D32333A))
                        )
                    }

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = if (course.hasLike)
                                painterResource(R.drawable.bookmark_fill)
                            else
                                painterResource(R.drawable.vector),
                            contentDescription = "Избранное",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = course.text,
                    color = Color(0xFFCCCCCC),
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${course.price} ₽",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Подробнее →",
                        color = Color(0xFF12B956),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}



