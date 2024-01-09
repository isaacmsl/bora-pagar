import type { PageUser } from '@/types/PageUser';
import axios, { Axios, type AxiosResponse } from 'axios'
export class UserService {
  private axiosInstance : Axios;

  public constructor() {
    this.axiosInstance = axios.create({
      baseURL: import.meta.env.VITE_API_URL + '/users',
    });
  }

  public async searchUsersByUsername(page : number, username? : string) : Promise<PageUser>{
    const response = await this.axiosInstance.get<any, AxiosResponse<PageUser>>('', {
      params: {
        partialUsername: username || '',
        page
      }
    });
    
    return response.data;
  }
}