storage "consul" {
  address = "127.0.0.1:8500"
  path    = "vault/"
}

listener "tcp" {
 address     = "127.0.0.1:8200"
 tls_disable = 1
}

VAULT_ADDR="http://127.0.0.1:8200"
api_addr = "http://127.0.0.1:8200"
tls_disable = 1
ui = true
