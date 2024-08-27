package com.example.dicerollerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun App(int1: Int,int2: Int) {

    val player1Chance = remember { mutableStateOf(true) }
    var score1 = remember { mutableStateOf(int1) }
    var score2 = remember { mutableStateOf(int2) }
    val diceImage = remember { mutableStateOf(R.drawable.dice_shield) }  // Default dice image
    // Default dice image

    val diceImages = listOf(
        R.drawable.dice_six_faces_1,
        R.drawable.dice_six_faces_2,
        R.drawable.dice_six_faces_3,
        R.drawable.dice_six_faces_4,
        R.drawable.dice_six_faces_5,
        R.drawable.dice_six_faces_6
    )
    if(score1.value>=20 || score2.value >=20){
        restore(score1.value,score2.value)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dice Roller App",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            text = if (player1Chance.value) "Player 1 Chance" else "Player 2 Chance",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffe400ff)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = diceImage.value),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (player1Chance.value) {
                    val number = Random.nextInt(6)  // Random number between 0 and 5
                    score1.value += number + 1  // Adjust number to be between 1 and 6
                    diceImage.value = diceImages[number]  // Update dice image
                    player1Chance.value = false
                }
            }, enabled = player1Chance.value) {
                Text(text = "Player 1")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = {
                if (!player1Chance.value) {
                    val number = Random.nextInt(6)  // Random number between 0 and 5
                    score2.value += number + 1  // Adjust number to be between 1 and 6
                    diceImage.value = diceImages[number]  // Update dice image
                    player1Chance.value = true
                }
            }, enabled = !player1Chance.value) {
                Text(text = "Player 2")
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "${score1.value}", color = Color.White)
            Spacer(modifier = Modifier.width(100.dp))
            Text(text = "${score2.value}", color = Color.White)
        }
    }
}
