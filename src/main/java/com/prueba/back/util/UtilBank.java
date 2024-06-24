package com.prueba.back.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class UtilBank {
	
	/**
	 * Metodo que suma 3 años partiendo de la fecha de hoy
	 * 
	 * @return String fecha en formato MM/yyyy(07/2027)
	 */
	public static String addThreeYearsToDate() {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaFutura = fechaActual.plusYears(3);
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("MM/yyyy");
		return fechaFutura.format(formateador);
	}
	
	/**
	 * Metodo que genera 10 numeros al azar con el fin de agregarlos al numero de tarjeta inicialmente
	 * 
	 * @return Long Numeros ya generados al azar
	 */
	public static String generateTenNumbersRandom() {
        Random random = new Random();
        LongStream numerosAleatorios = random.longs(5, 0, 100);
        
        String resultado = numerosAleatorios
                .mapToObj(Long::toString)
                .collect(Collectors.joining(""));
        
        return resultado;
	}
	
	/**
	 * Metodo que concatena 2 numeros de tipo long
	 * 
	 * @return Long Numeros concatenados
	 */
	public static String concatLongByLong(Long n, String b) {
		String numa = n.toString();

        return numa+b;
	}
	
	/**
	 * Metodo Validar que una fecha no sea mayor a 24 horas
	 * 
	 * @param validateDate Fecha a validar
	 * @return Boolean true si la fecha supera las 24 o false al contrario
	 */
	public static Boolean valitaeDaterohours24(LocalDateTime validateDate) {
		LocalDateTime now = LocalDateTime.now();
		
		Duration duration = Duration.between(validateDate, now);

        return (duration.toHours() >= 24);
	}

}
