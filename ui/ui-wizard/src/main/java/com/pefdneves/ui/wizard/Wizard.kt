package com.pefdneves.ui.wizard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.pefdneves.ui.common.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

private const val PAGER_AUTO_SCROLL_TIME_MS = 5000L
private const val PAGER_ANIMATION_TWEEN_MS = 600

@OptIn(ExperimentalPagerApi::class)
lateinit var pagerState: PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Wizard(onNavigate: (String) -> Unit) {
    pagerState = rememberPagerState(
        pageCount = getWizardEntries(LocalContext.current).size
    )

    var isStartButtonVisible by remember { mutableStateOf(false) }

    val entries = getWizardEntries(LocalContext.current)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(PAGER_AUTO_SCROLL_TIME_MS)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(PAGER_ANIMATION_TWEEN_MS)
            )
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect { page ->
            isStartButtonVisible = page == entries.size - 1
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            wizardTitleAndSubtitle()

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(4f)
                    .padding(
                        top = dimensionResource(R.dimen.wizard_pager_padding_top_bottom),
                        bottom = dimensionResource(R.dimen.wizard_pager_padding_top_bottom)
                    )
                    .background(Color.White),
            ) { pageIndex ->
                val item = entries[pageIndex]

                viewPagerCard(pageIndex)

                image(item)

                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(dimensionResource(R.dimen.wizard_title_subtitle_padding))
                ) {
                    title(item)
                    description(item)
                }
            }

            startButton(isStartButtonVisible, onNavigate)
        }
    }
}

@Composable
fun image(item: WizardEntry) {
    Image(
        painter = painterResource(id = item.image),
        contentDescription = null
    )
}

@Composable
private fun wizardTitleAndSubtitle() {
    Text(
        text = LocalContext.current.getString(R.string.wizard_title),
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.wizard_title_subtitle_padding),
            top = dimensionResource(R.dimen.wizard_title_subtitle_padding_top)
        ),
        fontWeight = FontWeight.Black
    )
    Text(
        text = LocalContext.current.getString(R.string.wizard_subtitle),
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(
            start = dimensionResource(R.dimen.wizard_title_subtitle_padding
            )
        ),
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Black
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun PagerScope.viewPagerCard(pageIndex: Int) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = calculateCurrentOffsetForPage(pageIndex).absoluteValue

                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
            }
            .padding(
                start = dimensionResource(R.dimen.wizard_pager_content_padding),
                end = dimensionResource(R.dimen.wizard_pager_content_padding)
            ),
        shape = RoundedCornerShape(dimensionResource(R.dimen.wizard_pager_content_corner)),
        backgroundColor = Color.Gray
    ) {
    }
}

@Composable
private fun description(item: WizardEntry) {
    Text(
        text = item.description,
        style = MaterialTheme.typography.body1,
        color = Color.Black,
        modifier = Modifier.padding(top = dimensionResource(R.dimen.wizard_pager_content_description_padding))
    )
}

@Composable
private fun title(item: WizardEntry) {
    Text(
        text = item.title,
        style = MaterialTheme.typography.h5,
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun startButton(
    isVisible: Boolean,
    onNavigate: (String) -> Unit
) {
    AnimatedVisibility(
        visible = isVisible
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(R.dimen.wizard_pager_padding_top_bottom),
                        end = dimensionResource(R.dimen.wizard_pager_padding_right_left)
                    ),
                onClick = {
                    onNavigate(Screen.Actions.route)
                }) {
                Text(
                    text = LocalContext.current.getString(R.string.wizard_btn_start),
                    color = Color.White
                )
            }
        }
    }
}