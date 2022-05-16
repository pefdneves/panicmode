package com.pefdneves.ui.wizard

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
lateinit var pagerState: PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Wizard(navController: NavHostController) {

    pagerState = rememberPagerState(
        pageCount = getWizardEntries(LocalContext.current).size
    )

    val launchedEffect = LaunchedEffect(Unit) {
        while(true) {
            yield()
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Text(
                text = LocalContext.current.getString(R.string.wizard_title),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                fontWeight = FontWeight.Black
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 40.dp,
                        bottom = 40.dp
                    )
                    .background(Color.Gray),
            ) { pageIndex ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            // TODO, transformation here
                        }
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                        ),
                    shape = RoundedCornerShape(24.dp),
                    backgroundColor = Color.Gray
                ) {
                }
            }
        }
    }
}