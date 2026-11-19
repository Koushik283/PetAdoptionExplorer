
package com.example.petadoptionexplorer.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

object IntentHelper {

    fun openAdoptionWebsite(context: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.aspca.org/adopt-pet/adoption-tips")
        )

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "No browser application found.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun sendAdoptionEmail(
        context: Context,
        dogName: String,
        applicantName: String,
        applicantEmail: String
    ) {
        val recipientEmail = "petadaptation@support.com"

        val subject = "Adoption Request - $dogName"

        val body = """
        Hello,

        I would like to enquire about adopting $dogName.

        Applicant Name: $applicantName
        Applicant Email: $applicantEmail

        Please contact me regarding the adoption process.

        Thank you.
    """.trimIndent()

        val emailUri = Uri.parse(
            "mailto:$recipientEmail" +
                    "?subject=${Uri.encode(subject)}" +
                    "&body=${Uri.encode(body)}"
        )

        val intent = Intent(Intent.ACTION_SENDTO, emailUri)

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                context,
                "No email application found.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
