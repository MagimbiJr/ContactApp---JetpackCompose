package com.tana.contactapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "col_id")
    var id: Int = 0,
    val name: String,
    val number: String,
    //@Ignore val imageDp: ImageVector = Icons.Default.Person
)
