package it;

public class AlgebricExp {

	static void removeDuplicate(AlgebricExpresssion algebricExpresssionTemp) {
		AlgebricExpresssion algebricExpresssion = new AlgebricExpresssion();
		algebricExpresssion.expresssion[0] =  algebricExpresssionTemp.expresssion[0];
		algebricExpresssion.expresssionLength =  1;
		for (int m = 1; m < algebricExpresssionTemp.expresssionLength; m++) {
			int visited = 0;
			for (int n = 0; n < algebricExpresssion.expresssionLength; n++) {
				if(algebricExpresssionTemp.expresssion[m].expression.equals(algebricExpresssion.expresssion[n].expression)) {
					algebricExpresssion.expresssion[n].coEff += algebricExpresssionTemp.expresssion[m].coEff;
					visited = 1; break;
				}
			}
			if(visited == 0) {
				algebricExpresssion.expresssion[algebricExpresssion.expresssionLength++] = algebricExpresssionTemp.expresssion[m];
			}
		}
		System.out.println(algebricExpresssion);
	}

	
	static Expresssion orderVariablesByAsciiCode(Expresssion expresssion) {
		for (int m = 0; m < expresssion.variableLength; m++) {
			for (int n = 0; n < expresssion.variableLength; n++) {
				if(expresssion.variablePower[m].ascii < expresssion.variablePower[n].ascii){
					Variable variableTemp = new Variable();
					variableTemp = expresssion.variablePower[m];
					expresssion.variablePower[m] = expresssion.variablePower[n];
					expresssion.variablePower[n] = variableTemp;
				}
			}
		}
		expresssion.toString();
		return expresssion;
	}
	
	static void doExpressionMultiply(AlgebricExpresssion[] algebricExpresssion,int algebricLength) {
		AlgebricExpresssion algebricExpresssionTemp = new AlgebricExpresssion();
		for (int i = 1; i < algebricLength; i++) {
			AlgebricExpresssion algebricExpresssionPrev = algebricExpresssion[i-1];
			AlgebricExpresssion algebricExpresssionCur = algebricExpresssion[i];
			int tempExpLength = 0;
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
						Variable variable = algebricExpresssionPrev.expresssion[j].variablePower[l];
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
							expresssion.variablePower[firstArrLen-1].ascii = variable.var.charAt(0) - 0;
						}
						l++;
					}
					expresssion = orderVariablesByAsciiCode(expresssion);
					algebricExpresssionTemp.expresssion[tempExpLength++] = expresssion;
					algebricExpresssionTemp.expresssionLength = tempExpLength;
				}
				algebricExpresssion[i] = algebricExpresssionTemp;
			}
		}
		removeDuplicate(algebricExpresssionTemp);
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
		getExpressionArray("(2x^2y+3xy^2z-xz^3)*(5xyz+3y^2z-2z)");
	}
}

//+10x^3y^2z +6x^2y^3z -4x^2yz +15x^2y^3z^2 +9xy^4z^2 -6xy^2z^2 -5x^2yz^4 -3xy^2z^4 +2xz^4
//10x3y2z    +6x2y3z   -4x2yz  +15x2y3z2    +9xy4z2 -6xy2z2 -5x2yz4 -3xy2z4 +2xz4
//+10x3y2z   +6x2y3z   -4x2yz  +15x2y3z2    +9xy4z2 -6xy2z2 -5x2yz4 -3xy2z4 +2xz4
class AlgebricExpresssion {
	
	Expresssion[] expresssion = new Expresssion[10];
	int expresssionLength;
	
	@Override
	public String toString() {
		String res = ""; 
		for(int i = 0 ; i < expresssionLength; i++) {
			res += expresssion[i].toString();
		}
		return res;
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
		expression = res;
		String coEffString = coEff<0 ? coEff+"" : "+" + coEff;
		return " "+coEffString + "" + expression;
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
		return var + pow;
	}
}
