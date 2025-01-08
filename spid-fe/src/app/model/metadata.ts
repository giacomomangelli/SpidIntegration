export interface Metadata {
  esito: string;
  metadata: string;
}

export interface AuthResponse {
  esito: string,
  issueIstant: string,
  uuid: string,
  b64RequestComp: string,
  ssoRequest: string
}
