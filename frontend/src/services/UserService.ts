import type { AppUser } from '@/types/AppUser';
import type { PageUser } from '@/types/PageUser';
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

  public async searchUserByGoogleId(googleId : string) : Promise<AppUser>{
    const response = await this.axiosInstance.get<any, AxiosResponse<AppUser>>('', {
      params: {
        googleId
      }
    });
    
    return response.data;
  }

  public async searchUsersByUsername(page : number, username? : string) : Promise<PageUser>{
    const response = await this.axiosInstance.get<any, AxiosResponse<PageUser>>('', {
      params: {
        partialUsername: username?.trim() || '',
        page
      }
    });
    
    return response.data;
  }
}
