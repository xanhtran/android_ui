package com.example.jetnote.ui.screens


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.unit.dp
import com.example.jetnote.domain.model.NoteModel
import com.example.jetnote.routing.Screen
import com.example.jetnote.ui.components.AppDrawer
import com.example.jetnote.ui.components.Note
import com.example.jetnote.ui.components.TopAppBar
import com.example.jetnote.viewmodel.MainViewModel
import kotlinx.coroutines.launch


    @Composable
    fun NotesScreen(viewModel: MainViewModel) {
        val notes: List<NoteModel> by viewModel
            .notesNotInTrash
            .observeAsState(listOf())

        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        Scaffold(
            topBar = {
                TopAppBar(
                    title  = "JetNotes",
                    icon = Icons.Filled.List,
                    onIconClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            scaffoldState = scaffoldState,
            drawerContent = {
                AppDrawer(
                    currentScreen = Screen.Notes,
                    closeDrawerAction = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    })
            },
            content = {
                if (notes.isNotEmpty()) {
                    NoteList(
                        notes = notes,
                        onNoteCheckedChange = {
                            viewModel.onNoteCheckedChange(it)
                        },
                        onNoteClick = { viewModel.onNoteClick(it) }
                    )
                }else{
                    Text(text="Opps")
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    }

                ) {
                    Text("+")
                }
            }
        )
    }


    @Composable
    private fun NoteList(
        notes: List<NoteModel>,
        onNoteCheckedChange: (NoteModel) -> Unit,
        onNoteClick: (NoteModel) -> Unit,
    ) {
        val backgroundShape : androidx.compose.ui.graphics.Shape = RoundedCornerShape(4.dp)
        LazyColumn{
            items(notes) {
                    items ->
                Note(items)
            }
        }
    }

