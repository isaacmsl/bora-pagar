import type { AppUser } from '@/types/AppUser';
import axios, { Axios, type AxiosResponse } from 'axios'
export class UserService {
  private axiosInstance : Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/users',
    });
  }
  
  public async welcomeUser(credential : string) : Promise<string> {
    const response = await this.axiosInstance.post<any, AxiosResponse<string>>('/welcome', {}, {
      headers: {
        credential
      }
    });
    
    return response.data;
  }

  public async searchUsersByUsername(username? : string) : Promise<AppUser[]>{
    const response = await this.axiosInstance.get<any, AxiosResponse<AppUser[]>>('', {
      params: {
        partialUsername: username || ''
      }
    });
    
    return response.data;
  }
}