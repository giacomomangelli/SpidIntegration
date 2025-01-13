import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {AuthResponse, Metadata} from '../model/metadata';

@Injectable({
  providedIn: 'root'
})
export class SpidService {

  BASE_URL = environment.url + 'spid';

  constructor(private readonly http: HttpClient) {
  }

  public getMetadata(clientId: string): Observable<Metadata> {
    console.log('Get Metadata for', clientId);
    return this.http.get<Metadata>(`${this.BASE_URL}/metadata?clientId=${clientId}`);
  }

  public getAuthRequest(clientId: string, idp: string): Observable<AuthResponse> {
    console.log('Get Auth Request');
    return this.http.get<AuthResponse>(`${this.BASE_URL}?clientId=${clientId}&idp=${idp}`);
  }

  public checkSessionArrived(clientId: string, idp: string): Observable<AuthResponse> {
    console.log('Get Auth Request');
    let obv = new Observable<any>();
    this.getMetadata(clientId).subscribe(metadata => {
      console.log(metadata);
      obv = this.getAuthRequest(clientId, idp);
    });
    return obv;
  }

}
