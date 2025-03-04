package be.bstorm.demospringapi.il.utils.specifications;

import be.bstorm.demospringapi.il.utils.request.SearchParam;
import org.springframework.data.jpa.domain.Specification;

import java.text.NumberFormat;

public interface SearchSpecification {

    static <T> Specification<T> search(SearchParam<T> searchParam){
        return (root, query, cb) -> switch (searchParam.op()) {
            case EQ -> cb.equal(root.get(searchParam.field()),searchParam.value());
            case NE -> cb.notEqual(root.get(searchParam.field()),searchParam.value());
            case GT -> {
                if(!(searchParam.value() instanceof Number)) throw new RuntimeException("Value must be a number");
                yield cb.gt(root.get(searchParam.field()),(Number) searchParam.value());
            }
            case GTE -> {
                if(!(searchParam.value() instanceof Number)) throw new RuntimeException("Value must be a number");
                yield cb.ge(root.get(searchParam.field()),(Number) searchParam.value());
            }
            case LT -> {
                if(!(searchParam.value() instanceof Number)) throw new RuntimeException("Value must be a number");
                yield cb.lt(root.get(searchParam.field()),(Number) searchParam.value());
            }
            case LTE -> {
                if(!(searchParam.value() instanceof Number)) throw new RuntimeException("Value must be a number");
                yield cb.le(root.get(searchParam.field()),(Number) searchParam.value());
            }
            case START -> cb.like(root.get(searchParam.field()),searchParam.value() + "%");
            case END -> cb.like(root.get(searchParam.field()),"%" + searchParam.value());
            case CONTAINS -> cb.like(root.get(searchParam.field()),"%" + searchParam.value() + "%");
            case IN -> root.get(searchParam.field()).in(((String)searchParam.value()).split(","));
            case NIN -> cb.not(root.get(searchParam.field())).in(((String)searchParam.value()).split(","));
        };
    }
}
/*Opérateur	Description	SQL équivalent
EQ (Equal)	Vérifie si la valeur est égale à celle du champ.	WHERE field = value
NE (Not Equal)	Vérifie si la valeur est différente de celle du champ.	WHERE field <> value
GT (Greater Than)	Vérifie si la valeur est strictement supérieure.	WHERE field > value
GTE (Greater Than or Equal)	Vérifie si la valeur est supérieure ou égale.	WHERE field >= value
LT (Less Than)	Vérifie si la valeur est strictement inférieure.	WHERE field < value
LTE (Less Than or Equal)	Vérifie si la valeur est inférieure ou égale.	WHERE field <= value
START (Commence par)	Vérifie si la valeur commence par une chaîne donnée.	WHERE field LIKE 'value%'
END (Se termine par)	Vérifie si la valeur se termine par une chaîne donnée.	WHERE field LIKE '%value'
CONTAINS (Contient)	Vérifie si la valeur contient une sous-chaîne.	WHERE field LIKE '%value%'
IN (Dans une liste)	Vérifie si la valeur est dans une liste donnée.	WHERE field IN (value1, value2, ...)
NIN (Not IN)	Vérifie si la valeur n'est pas dans une liste.	WHERE field NOT IN (value1, value2, ...)*/