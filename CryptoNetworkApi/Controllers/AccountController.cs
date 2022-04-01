using CryptoNetworkApi.Models;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;

namespace CryptoNetworkApi.Controllers
{
    [Route("crypto-network/account")]
    [ApiController]
    public class AccountController : Controller
    {
        public static List<User> _users = new List<User>();

        [HttpGet("check")]
        public IActionResult CheckUser(string login, string password)
        {
            User user = _users.FirstOrDefault(user => user.Login == login && user.Password == password);
            if (user != null)
            {
                return Ok(user.SerializeUserInfo());
            }
            return BadRequest("Incorrect account");
        }

        [HttpPost("create")]
        public IActionResult PostUser(string login, string password, string username)
        {
            _users.Add(new User(login, password, username));
            return Ok("Account created");
        }

        [HttpDelete("delete")]
        public IActionResult DeleteUser(string login, string password)
        {
            User user = _users.FirstOrDefault(user => user.Login == login && user.Password == password);
            if (user != null)
            {
                _users.Remove(user);
                return Ok("User deleted");
            }
            return BadRequest("Incorrect account");
        }

        [HttpGet("get-all")]
        public IActionResult GetAllUsers()
        {
            return Ok(System.Text.Json.JsonSerializer.Serialize(_users));
        }
    }
}
