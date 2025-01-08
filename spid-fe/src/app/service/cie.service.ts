import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Metadata} from '../model/metadata';

@Injectable({
  providedIn: 'root'
})
export class CieService {

  BASE_URL = environment.url + 'cie';

  constructor(private readonly http: HttpClient) {
  }

  public getMetadata(clientId: string): Observable<Metadata> {
    console.log('Get Metadata');
    return this.http.get<Metadata>(`${this.BASE_URL}/metadata?client_id=${clientId}`);
  }

  public getAuthRequest(clientId: string): Observable<any> {
    console.log('Get Auth Request');
    let obv = new Observable<any>();
    this.getMetadata(clientId).subscribe(metadata => {
      console.log(metadata);
      obv = this.http.get(`${this.BASE_URL}?client_id=${clientId}`);
    });
    return obv;
  }
}
