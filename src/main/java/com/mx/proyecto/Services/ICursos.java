package com.mx.proyecto.Services;

import com.mx.proyecto.Dto.Cursos;
import com.mx.proyecto.Dto.ResponseDto;

public interface ICursos {

	ResponseDto getCursos(); //ESTE METODO OBTIENE ASPIRANTES
	ResponseDto insertCursos(Cursos nuevoCurso);
	ResponseDto updateCursos(Cursos curso);
	ResponseDto deleteCursos(Cursos curso);
	ResponseDto insertCursosMasivos(Cursos[] cursos);

}