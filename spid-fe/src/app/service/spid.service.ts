import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SpidService {

  BASE_URL = environment.url + 'spid/';

  constructor(private readonly http: HttpClient) {
  }

  public getAuthRequest(clientId: string, idp: string): Observable<any> {
    console.log('Get Auth Request');
    return this.http.get(`${this.BASE_URL}?client_id=${clientId}&idp=${idp}`);
  }

  public redirectToIdp(ssoRequest: string) {
    return
  }
}
