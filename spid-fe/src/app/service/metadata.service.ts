import {Injectable} from '@angular/core';
import {Metadata} from '../model/metadata';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MetadataService {

  BASE_URL = environment.url + 'metadata/';

  constructor(private readonly http: HttpClient) {
  }

  public getMetadata(clientId: string): Observable<Metadata> {
    console.log('Get Metadata');
    return this.http.get<Metadata>(`${this.BASE_URL}?client_id=${clientId}`);
  }

}
