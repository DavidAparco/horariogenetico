package ar.com.protean.horario.genetico;

import java.util.Collection;
import java.util.HashSet;

import ar.com.protean.horario.genetico.enums.DiaEnum;
import ar.com.protean.horario.genetico.enums.HorarioEnum;
import ar.com.protean.horario.genetico.enums.MateriaEnum;

public class Profesor {
	private MatrixCurso matrixCurso;
	private Collection<MateriaEnum> materias;
	private String nombre;
	private int horasACubrir;
	
	public Profesor(){
		this.matrixCurso = new MatrixCurso();
	}
	
	/**
	 * Colecci�n de asignaturas que el profesor est� capacitado para dictar
	 */
	public Collection<MateriaEnum> getMaterias() {
		return materias;
	}
	
	/**
	 * Agrego una asignatura de las cuales el profesor est� capacitado para dictar
	 * @param materia
	 */
	public void addMateria(MateriaEnum materia) {
		if (this.materias == null){
			this.materias = new HashSet<MateriaEnum>();
		}
		this.materias.add(materia);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private MatrixCurso getMatrixCurso(){
		if (this.matrixCurso == null){
			this.matrixCurso = new MatrixCurso();
		}
		return this.matrixCurso;
	}
	
	/**
	 * Si a este profesor le asignan una materia, entonces le indico a dicha materia
	 * qui�n es el dictador, pero si el profesor ya ten�a cargado NO_DISPONIBLE en el
	 * mismo horario, dejo ese estado para mostrar que la materia no podr�a dictarse 
	 * en el horario asignado.<l> 
	 * 
	 * Se sabe que la materia se intenta dictar en el mismo horario, porque la materia 
	 * tambi�n guarda la informaci�n del d�a y la hora que se pretende dictar.<l>
	 * 
	 * @param dia
	 * @param hora
	 * @param materia
	 */
	@SuppressWarnings("deprecation")
	public void asignoMateria(DiaEnum dia, HorarioEnum hora, Materia materia){
		materia.setDictador(this);
		materia.setHorario(dia, hora);
		Materia materiaDelProfe = this.getMatrixCurso().getMateria(dia, hora);
		if (materiaDelProfe == null || !Materia.NO_DISPONIBLE.getNombre().equals(materiaDelProfe.getNombre())){
			this.getMatrixCurso().setMateria(dia, hora, materia);
		}
	}

	public Materia getMateria(DiaEnum dia, HorarioEnum hora) {
		return this.matrixCurso.getMateria(dia, hora);
	}

	public int getHorasACubrir() {
		return this.horasACubrir;
	}

	public void setHorasACubrir(int horasACubrir) {
		this.horasACubrir = horasACubrir;
	}
}