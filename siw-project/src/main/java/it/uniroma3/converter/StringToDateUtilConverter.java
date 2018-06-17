package it.uniroma3.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;


//@Component
public class StringToDateUtilConverter {
/*
	@Override
	public Date convert(String value) {
		return null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try{
			d = df.parse(value);
		}
		catch(Exception e) {
			return null;
		}
		return d;

	}

	@Override
	public JavaType getInputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType getOutputType(TypeFactory typeFactory) {
		// TODO Auto-generated method stub
		return null;
	}

*/
}
