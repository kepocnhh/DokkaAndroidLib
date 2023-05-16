package dokka.android.lib

import android.content.Context
import android.widget.Toast

/**
 * Shows the view for the specified [message] and [duration].
 * @param message [Toast] will show this text.
 * @param duration How long to display the message. Default is [Toast.LENGTH_SHORT].
 * @receiver [Toast] needs [Context].
 * @author [Stanley Wintergreen](https://github.com/kepocnhh)
 * @since 0.0.1
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
