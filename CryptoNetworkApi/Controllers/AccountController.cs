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
            if (_users.Any(user => user.Login == login && user.Password == password))
            {
                return Ok("Correct account");
            }
            return BadRequest("Incorrect account");
        }

        [HttpPost("create")]
        public IActionResult PostUser(string login, string password)
        {
            _users.Add(new User(login, password));
            return Ok("Account created");
        }

        [HttpGet("print-debug")]
        public IActionResult Print()
        {
            foreach (var user in _users)
            {
                System.Diagnostics.Debug.WriteLine($"User: {user.Login}");
            }
            return Ok("Printed");
        }

        [HttpGet("get-all")]
        public IActionResult GetAllUsers()
        {
            return Ok(System.Text.Json.JsonSerializer.Serialize(_users));
        }
    }
}
