
package com.example.petadoptionexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.petadoptionexplorer.model.AdoptionRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionRequestScreen(
    dogId: Int,
    dogName: String,
    onBack: () -> Unit,
    onSubmit: (AdoptionRequest) -> Unit
) {
    var applicantName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adoption Application") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Text(
                text = "Apply to Adopt $dogName",
                style = MaterialTheme.typography.headlineSmall
            )

            Text("Please provide your details to submit an adoption request.")

            OutlinedTextField(
                value = applicantName,
                onValueChange = {
                    applicantName = it
                    errorMessage = ""
                },
                label = { Text("Full Name *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = ""
                },
                label = { Text("Email Address *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it.filter { char ->
                        char.isDigit() || char == '+'
                    }
                    errorMessage = ""
                },
                label = { Text("Phone Number *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    errorMessage = ""
                },
                label = { Text("Residential Address *") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = {
                    Text("Why would you like to adopt? (Optional)")
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = {
                    when {
                        applicantName.isBlank() ->
                            errorMessage = "Please enter your full name."

                        !email.trim().matches(
                            Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
                        ) ->
                            errorMessage = "Please enter a valid email address."

                        phone.count { it.isDigit() } < 10 ->
                            errorMessage = "Please enter a valid phone number."

                        address.isBlank() ->
                            errorMessage = "Please enter your residential address."

                        else -> {
                            onSubmit(
                                AdoptionRequest(
                                    dogId = dogId,
                                    dogName = dogName,
                                    applicantName = applicantName.trim(),
                                    email = email.trim(),
                                    phone = phone.trim(),
                                    address = address.trim(),
                                    message = message.trim()
                                )
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Adoption Request")
            }
        }
    }
}
