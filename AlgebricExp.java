package it;

import java.util.Arrays;

public class AlgebricExp {

	static void doExpressionMultiply(AlgebricExpresssion[] algebricExpresssion,int algebricLength) {
		for (int i = 1; i < algebricLength; i++) {
			AlgebricExpresssion algebricExpresssionPrev = algebricExpresssion[i-1];
			AlgebricExpresssion algebricExpresssionCur = algebricExpresssion[i];
			System.out.println(algebricExpresssionCur +"  *  " + algebricExpresssionPrev);
			for (int j = 0; j < algebricExpresssionPrev.expresssionLength; j++) {
				for (int k = 0; k < algebricExpresssionCur.expresssionLength; k++) {
					Expresssion expresssion = new Expresssion();
					expresssion.coEff = algebricExpresssionCur.expresssion[k].coEff * algebricExpresssionPrev.expresssion[j].coEff;
					for (int l = 0; l < algebricExpresssionCur.expresssion[k].variableLength; l++) {
						Variable variable = new Variable();
						variable.power = algebricExpresssionCur.expresssion[k].variablePower[l].power;
						variable.var =	algebricExpresssionCur.expresssion[k].variablePower[l].var;
						variable.ascii = algebricExpresssionCur.expresssion[k].variablePower[l].var.charAt(0) - 0;
						expresssion.variablePower[l] = variable;
						expresssion.variableLength = algebricExpresssionCur.expresssion[k].variableLength;
					}
					int l = 0;
					while(l < algebricExpresssionPrev.expresssion[j].variableLength) {
						Variable variable = algebricExpresssionPrev.expresssion[j].variablePower[0];
						int visited = 0;
						int firstArrLen = expresssion.variableLength;
						for (int m = 0; m < algebricExpresssionCur.expresssion[k].variableLength; m++) {
							Variable variable2 = expresssion.variablePower[m];
							if(variable.var.equals(variable2.var)) {
								expresssion.variablePower[m].power += variable.power;
								visited = 1; break;
							}
						}
						if (visited == 0) {
							expresssion.variablePower[firstArrLen++] = variable;
							expresssion.variableLength = firstArrLen;
							expresssion.variablePower[firstArrLen].ascii = variable.var.charAt(0) - 0;
						}
						l++;
					}
					System.out.println(expresssion);
				}
			}
		}
	}
	
	static void getExpressionArray(String input) {
		input = input.replace("(", "");
		input = input.replace(")", "");
		input = input.replace("-", "-=");
		String[] splitByAstrix = input.split("[*]");
		AlgebricExpresssion[] algebricExpresssion = new AlgebricExpresssion[10];
		for (int i = 0; i < splitByAstrix.length; i++) {
			AlgebricExpresssion algebricExp = new AlgebricExpresssion();
			Expresssion[] expresssionArr = new Expresssion[10];
			String[] splitBySymbol = splitByAstrix[i].split("[+-]");
			for (int j = 0; j < splitBySymbol.length; j++) {
				Expresssion expresssion = new Expresssion(splitBySymbol[j]);
				expresssionArr[j] = expresssion;
			}
			algebricExp.expresssion = expresssionArr;
			algebricExp.expresssionLength = splitBySymbol.length;
			algebricExpresssion[i] = algebricExp;
		}
		doExpressionMultiply(algebricExpresssion, splitByAstrix.length);
	}
	
	public static void main(String[] args) {
		getExpressionArray("(2xy+4x^2y)*(2x^2y+6xy)");
	}
}

class AlgebricExpresssion {
	Expresssion[] expresssion = new Expresssion[10];
	int expresssionLength;
	
	@Override
	public String toString() {
		String res = ""; 
		for(int i = 0 ; i < expresssionLength; i++) {
			res += expresssion[i].toString();
		}
		return res + "  -->length is " + expresssionLength;
	}
}

class Expresssion {
	
	int coEff;
	Variable[] variablePower = new Variable[10];
	String expression;//x^2y^2z
	int variableLength;
	
	@Override
	public String toString() {
		String res = ""; 
		for(int i = 0 ; i < variableLength; i++) {
			res += variablePower[i].toString();
		}
		return " " + coEff + res;
	}
	Expresssion(){
		
	}
	Expresssion(String val) {
		val = val.replace("=", "-");
		int sign = val.charAt(0) == '-' ? -1 : 1 ;
		if(sign==-1) {val = val.substring(1);}
		int endIndex = getDigitEnd(val);
		coEff = endIndex != 0 ? Integer.parseInt(val.substring(0,endIndex)) : 1;
		coEff *= sign;
		int varLength = 0;
		val = val.substring(endIndex).replace("^", "");
		for(int i = 0; i < val.length() ; i ++ ) {
			int value = val.charAt(i) - '0';
			if( value >= 9 || value <= 0) {
				Variable variablePowerObj = new Variable();
				variablePowerObj.var = String.valueOf(val.charAt(i));
				int varPower = getDigitEnd(val.substring(++i));
				variablePowerObj.power = varPower != 0 ? Integer.parseInt(val.substring(i,i+varPower)) : 1;
				i = i + varPower - 1;
				variablePower[varLength++] = variablePowerObj;
			}
		}
		variableLength = varLength;
		for(int i = 0; i < variableLength ; i ++ ) {
			Variable variablePowerObj = variablePower[i];
		}
 	}
	private int getDigitEnd(String val) {
		int endIndex = 0;
		for (int i = 0; i < val.length() ; i++) {
			int value = val.charAt(i) - '0';
			if( value <= 9 && value >= 0) {
				endIndex++;
			} else {
				break;
			}
		}
		return endIndex;
	}
	
}

class Variable {
	String var;
	int power;
	int ascii;
	@Override
	public String toString() {
		String pow = power > 1 ? "^"+power : "";
		return var + "(" + ascii + ")" + pow;
	}
}
