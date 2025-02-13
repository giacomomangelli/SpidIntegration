export interface UserSpidData {
  esito?: string,
  provider_id?: string,
  attributi_utente?: AttributiUtenteSpid,
  response_id?: string,
  info_tracciatura?: InfoTracciatura
}

export interface AttributiUtenteSpid {
  spidCode?: string,
  name?: string,
  familyName?: string,
  fiscalNumber?: string,
  email?: string,
  gender?: string,
  dateOfBirth?: string,
  placeOfBirth?: string,
  countyOfBirth?: string,
  idCard?: string,
  address?: string,
  digitalAddress?: string,
  expirationDate?: string,
  mobilePhone?: string,
  ivaCode?: string,
  registeredOffice?: string,
}

export interface InfoTracciatura {
  response?: string,
  response_id?: string,
  response_issue_instant?: string,
  response_issuer?: string,
  assertion_id?: string,
  assertion_subject?: string,
  assertion_subject_name_qualifier?: string,
}
