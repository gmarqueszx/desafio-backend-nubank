package space.gmarqueszx.desafio_api_nubank.handler;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import space.gmarqueszx.desafio_api_nubank.exception.ClienteNaoEncontradoException;
import space.gmarqueszx.desafio_api_nubank.exception.CpfEmUsoException;
import space.gmarqueszx.desafio_api_nubank.exception.NegocioException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                             HttpHeaders headers,
                                                                             HttpStatusCode status,
                                                                             WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = String.format("O recurso '%s' que você tentou acessar é inexistente.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                                   HttpHeaders headers,
                                                                                   HttpStatusCode status,
                                                                                   WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "Método HTTP não aceito nesse endpoint.";

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "URL com tipo não inesperado.";

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @Override
    public @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "O corpo da requisição está inválido ou mal formatado.";


        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    public @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "A validação do argumento falhou.";

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Problem problem = createProblemBuilder(status, problemType, detail, errors).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<?> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex,
                                                                 WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.CLIENTE_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CpfEmUsoException.class)
    public ResponseEntity<?> handleCpfEmUsoException(CpfEmUsoException ex,
                                                     WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.CPF_EM_USO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex,
                                                    WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                                       @Nullable Object body,
                                                                       HttpHeaders headers,
                                                                       HttpStatusCode status,
                                                                       WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status,
                                                        ProblemType problemType, String detail,
                                                        Map<String, String> errors) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .errors(errors);
    }
}
