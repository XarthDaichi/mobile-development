package unaeif411.delta.petmatch

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("\${url.pets}")
class PetController(private val animalService: AnimalService) {

    /**
     * WS to find all elements of type Animal
     * @return A list of elements of type Animal
     */
    @GetMapping
    @ResponseBody
    fun findAll() = animalService.findAll()

    /**
     * WS to find one Pet by the id
     * @param id to find Pet
     * @return the Pet found
     */
    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = animalService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody animalInput: AnimalDetails) : AnimalDetails? {
        return animalService.create(animalInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody animalInput: AnimalDetails) : AnimalDetails? {
        return animalService.update(animalInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        animalService.deleteById(id)
    }
}

@RestController
@RequestMapping("\${url.reports}")
class ReportController(private val reportService: ReportService) {
    @GetMapping
    @ResponseBody
    fun findAll() = reportService.findAll()

    @GetMapping("{id}")
    @ResponseBody
    fun findById(@PathVariable id:Long) = reportService.findById(id)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody reportInput: ReportDetails) : ReportDetails? {
        return reportService.create(reportInput)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun update(@RequestBody reportInput: ReportDetails) : ReportDetails? {
        return reportService.update(reportInput)
    }

    @DeleteMapping("{id}")
    @ResponseBody
    fun deleteById(@PathVariable id:Long) {
        reportService.deleteById(id)
    }
}

@RestController
@RequestMapping("\${url.ia}")
class IAController(private val aiService: AIService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun create(@RequestBody prompt: String) : String? {
        return aiService.create(prompt)
    }
}