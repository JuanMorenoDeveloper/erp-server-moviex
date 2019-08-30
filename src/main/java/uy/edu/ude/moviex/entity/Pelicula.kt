package uy.edu.ude.moviex.entity

import org.hibernate.annotations.DynamicUpdate
import org.hibernate.validator.constraints.URL
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@DynamicUpdate
class Pelicula {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = 0
  @NotBlank
  @NotNull
  @Size(max = 256)
  var titulo: String = ""
  @Size(max = 256)
  @NotNull
  @NotBlank
  var director: String = ""
  @NotNull
  @Size(max = 256)
  var fechaEstreno: LocalDate = LocalDate.now()
  @NotNull
  @Size(max = 256)
  @URL(regexp = "^(http|https)")
  var poster: String = ""

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genero_id")
  @NotNull
  var genero: Genero = Genero()

  constructor() {}

  constructor(id: Long, @NotNull titulo: String,
              @NotNull director: String,
              @Email @NotNull fechaEstreno: LocalDate, @NotNull direccion: String,
              genero: Genero, poster: String) {
    this.id = id
    this.titulo = titulo
    this.director = director
    this.fechaEstreno = fechaEstreno
    this.genero = genero
    this.poster = poster
  }

  override fun equals(o: Any?): Boolean {
    if (this === o) {
      return true
    }
    if (o !is Pelicula) {
      return false
    }
    val pelicula = o as Pelicula?
    return id != null && id == pelicula!!.id
  }

  override fun hashCode(): Int {
    return 31
  }

  override fun toString(): String {
    return "Pelicula{" +
        "id=" + id +
        ", titulo='" + titulo + '\''.toString() +
        ", director='" + director + '\''.toString() +
        ", fechaEstreno='" + fechaEstreno + '\''.toString() +
        ", genero='" + genero + '\''.toString() +
        ",poster='" + poster + '\''.toString() +
        '}'.toString()
  }
}
