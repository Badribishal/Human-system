package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.EmotionCatalog
import com.example.model.SystemCalculator
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Human System", appName)
  }

  @Test
  fun `verify emotions catalog count exceeds 121`() {
    assertTrue(EmotionCatalog.allEmotions.size >= 121)
  }

  @Test
  fun `test system state calculation`() {
    val selected = EmotionCatalog.allEmotions.filter { it.id == "anxious" || it.id == "overwhelmed" }
    val diagnosis = SystemCalculator.calculateState(selected, intensity = 4)
    assertTrue(diagnosis.valence < 0)
    assertTrue(diagnosis.recommendations.isNotEmpty())
  }
}
