package br.rosaluz.banking.system.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Component
@FeignClient(name = "account", url = "${banking-system-account-url}")
public interface AccountFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/banking/system/account/create/{userId}")
    @ResponseBody AccountDTO createAccount(@PathVariable final String userId);
}
