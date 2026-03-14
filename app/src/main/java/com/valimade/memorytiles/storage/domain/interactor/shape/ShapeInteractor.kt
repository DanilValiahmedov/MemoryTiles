package com.valimade.memorytiles.storage.domain.interactor.shape

import com.valimade.memorytiles.settings.data.shape.ShapeTiles
import com.valimade.memorytiles.storage.data.shape.ShapeStore
import kotlinx.coroutines.flow.Flow

class ShapeInteractor(
    val shapeStore: ShapeStore,
) {
    suspend fun saveShape(shape: ShapeTiles) {
        shapeStore.saveShape(shape)
    }

    fun getShape(): Flow<ShapeTiles> {
        return shapeStore.getShape()
    }
}