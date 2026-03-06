package com.valimade.memorytiles.storage.domain.shape

import com.valimade.memorytiles.settings.data.shape.ShapeTiles
import com.valimade.memorytiles.storage.data.shape.ShapeStore
import kotlinx.coroutines.flow.Flow

class ShapeInteractor(
    val shapeStore: ShapeStore,
) {
    suspend fun save(shape: ShapeTiles) {
        shapeStore.saveShape(shape)
    }

    fun getTheme(): Flow<ShapeTiles> {
        return shapeStore.getShape()
    }

}