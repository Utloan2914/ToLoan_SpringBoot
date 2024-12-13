package vn.techzen.academy_pnv_12.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.techzen.academy_pnv_12.Model.CalculationResult;
import vn.techzen.academy_pnv_12.Model.ErrorResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @GetMapping("/api/calculate")
    public ResponseEntity<?> calculate(
            @RequestParam(value = "firstNumberStr", defaultValue = "") String firstNumberStr,
            @RequestParam(value = "secondNumberStr", defaultValue = "") String secondNumberStr,
            @RequestParam(value = "operator", defaultValue = "") String operator) {

        Map<String, String> errors = new HashMap<>();

        if (firstNumberStr.isEmpty()) {
            errors.put("firstNumber", "First number cannot be empty.");
        } else if (!isDouble(firstNumberStr)) {
            errors.put("firstNumber", "First number must be a valid number.");
        }

        if (secondNumberStr.isEmpty()) {
            errors.put("secondNumber", "Second number cannot be empty.");
        } else if (!isDouble(secondNumberStr)) {
            errors.put("secondNumber", "Second number must be a valid number.");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(errors));
        }

        double firstNumber = Double.parseDouble(firstNumberStr);
        double secondNumber = Double.parseDouble(secondNumberStr);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    errors.put("secondNumber", "The number cannot be divided by zero.");
                    return ResponseEntity.badRequest().body(new ErrorResponse(errors));
                }
                result = firstNumber / secondNumber;
                break;
            default:
                errors.put("operatorError", "Invalid operation.");
                return ResponseEntity.badRequest().body(new ErrorResponse(errors));
        }

        return ResponseEntity.ok(new CalculationResult(result));
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
