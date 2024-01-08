import type { AppUser } from '@/types/AppUser';
import axios, { Axios, type AxiosResponse } from 'axios'
export class UserService {
  private axiosInstance : Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/users',
    });
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