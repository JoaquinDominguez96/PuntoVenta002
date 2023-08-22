package com.mx.proyecto.Repository;

import java.util.List;

import com.mx.proyecto.Dto.Cursos;


public interface ICursosRepository {
	List<Cursos> getCursos();
	Integer insertCursos(Cursos nuevoCurso);
	Integer updateCursos(Cursos curso);
	Integer deleteCursos(Cursos curso);
	int[][] insertCursosMasivo(List<Cursos> cursos);
	List<String> getNomCursos();
//	List<Cursos> getIdCursos();
}