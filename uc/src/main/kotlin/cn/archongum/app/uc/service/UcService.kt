package cn.archongum.app.uc.service

import cn.archongum.app.uc.api.PetsApiDelegate
import cn.archongum.app.uc.model.Pet
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
open class UcService : PetsApiDelegate {

    companion object {
        var pets: MutableMap<Long, Pet> = HashMap(4)

        init {
            for (i in 0L..3L) {
                pets[i] = Pet(id = i, name = "pet:$i")
            }
        }
    }

    override fun findPetById(id: Long): ResponseEntity<Pet> {
        val pet: Pet? = pets[id]
        return ResponseEntity<Pet>(pet, HttpStatus.OK)
    }

}
